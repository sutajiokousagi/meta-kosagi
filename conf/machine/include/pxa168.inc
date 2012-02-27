SOC_FAMILY = "pxa168"

require conf/machine/include/tune-arm926ejs.inc

# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r14"


PREFERRED_PROVIDER_virtual/kernel = "linux-silvermoon"

KERNEL_IMAGETYPE = "zImage"

SERIAL_CONSOLE = "115200 ttyS0"

#UBOOT_ENTRYPOINT = "0x80008000"
#UBOOT_LOADADDRESS = "0x80008000"

EXTRA_IMAGEDEPENDS += "u-boot silvermoon-blobs makedisk-native config-util-native regutil compat-wireless-ath9k-htc ath9k-firmware"

# Match current Silvermoon devices
IMAGE_ROOTFS_SIZE = "512000"

IMAGE_POSTPROCESS_COMMAND += "kosagi_make_rom;"

kosagi_make_rom() {
    install -d ${DEPLOY_DIR_IMAGE}

    # Create the config block header
    config_util --cmd=create \
        --mbr=/dev/zero \
        --configname=kovan \
        --build_ver=1000 --force --pad \
        --blockdef=${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin,1507328,u-bt,1,0,0,0 \
        --blockdef=${DEPLOY_DIR_IMAGE}/zImage-${MACHINE}.bin,3932160,krnA,1,0,0,0 \
        --blockdef=/dev/null,3932160,krnB,1,0,0,0 \
        --blockdef=/dev/null,16384,cpid,1,0,0,0 \
        --blockdef=/dev/null,1024,dcid,1,0,0,0 \
        --blockdef=/dev/null,340992,720p,1,0,0,0 \
        --blockdef=/dev/null,1843200,logo,1,0,0,0 \
        > ${DEPLOY_DIR_IMAGE}/config_block.bin


    # Populate the config block
    rm -f ${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin
    touch ${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin
    dd conv=notrunc of=${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin seek=0 count=96 if=${DEPLOY_DIR_IMAGE}/obm.bin
    dd conv=notrunc of=${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin seek=96 count=32 if=${DEPLOY_DIR_IMAGE}/config_block.bin
    dd conv=notrunc of=${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin seek=128 count=2944 if=${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin
    dd conv=notrunc of=${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin seek=3072 count=7680 if=${DEPLOY_DIR_IMAGE}/zImage-${MACHINE}.bin
    dd conv=notrunc of=${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin seek=10752 count=7680 if=/dev/null
    dd conv=notrunc of=${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin seek=18466  count=666 if=/dev/null
    dd conv=notrunc of=${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin seek=19132 count=3600 if=/dev/null



    # Make full boot ROM
    rm -f ${DEPLOY_DIR_IMAGE}/rom-${MACHINE}-${IMAGE_BASENAME}.img.gz
    makedisk -o ${DEPLOY_DIR_IMAGE}/rom-${MACHINE}-${IMAGE_BASENAME}.img \
             -a 16M:0x53:${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.bin \
             -a ${ROOTFS_SIZE}k:0x83:${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.ext3
    gzip ${DEPLOY_DIR_IMAGE}/rom-${MACHINE}-${IMAGE_BASENAME}.img
}
