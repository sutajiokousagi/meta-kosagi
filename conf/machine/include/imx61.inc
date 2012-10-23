require conf/machine/include/tune-cortexa9.inc

PREFERRED_PROVIDER_virtual/bootloader = "u-boot-imx"
PREFERRED_PROVIDER_virtual/kernel = "linux-novena"

# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r0"
KERNEL_IMAGETYPE = "uImage"
KERNEL_ALT_IMAGETYPE = "LOADADDR=0x10008000"

EXTRA_IMAGEDEPENDS += "u-boot-imx makedisk-native genfatfs-native"
UBOOT_MACHINE = "mx6qsabrelite_config"
UBOOT_SUFFIX = "imx"
UBOOT_MAKE_TARGET = "u-boot.imx"

IMAGE_ROOTFS_SIZE = "512000"
IMAGE_FSTYPES += "ext3"

KERNEL_DEVICETREE = "arch/arm/boot/dts/novena.dts"
IMAGE_POSTPROCESS_COMMAND += "novena_make_rom;"

novena_make_rom() {
    install -d ${DEPLOY_DIR_IMAGE}
    # Create the boot partition
    rm -rf ${DEPLOY_DIR_IMAGE}/fat-boot
    mkdir -p ${DEPLOY_DIR_IMAGE}/fat-boot
    cp ${DEPLOY_DIR_IMAGE}/uImage-${MACHINE}.bin ${DEPLOY_DIR_IMAGE}/fat-boot/
    cp ${DEPLOY_DIR_IMAGE}/uImage-novena.dtb ${DEPLOY_DIR_IMAGE}/fat-boot/
    cp ${DEPLOY_DIR_IMAGE}/boot.scr ${DEPLOY_DIR_IMAGE}/fat-boot/
    genfatfs -d ${DEPLOY_DIR_IMAGE}/fat-boot -b 32768 boot-${MACHINE}.vfat

    # Generate the disk image.  Pad the first partition with 512k for U-Boot
    makedisk -o ${DEPLOY_DIR_IMAGE}/rom-${MACHINE}-${IMAGE_BASENAME}.img \
             -p 512K \
             -a 16M:0x0b:${DEPLOY_DIR_IMAGE}/boot-${MACHINE}.vfat \
             -a ${ROOTFS_SIZE}k:0x83:${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.ext3

    # Slot the U-Boot image into the first 512k (after the first 1024 bytes)
    dd if=${DEPLOY_DIR_IMAGE}/${UBOOT_MAKE_TARGET} \
       of=${DEPLOY_DIR_IMAGE}/rom-${MACHINE}-${IMAGE_BASENAME}.img \
       bs=512 \
       conv=notrunc \
       seek=2

    # Compress ROM image
    rm -f ${DEPLOY_DIR_IMAGE}/rom-${MACHINE}-${IMAGE_BASENAME}.img.gz
    /bin/gzip ${DEPLOY_DIR_IMAGE}/rom-${MACHINE}-${IMAGE_BASENAME}.img
}
