require conf/machine/include/tune-cortexa9.inc

# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r0"

KERNEL_IMAGETYPE = "zImage"

EXTRA_IMAGEDEPENDS += "u-boot"

# Match current Silvermoon devices
IMAGE_ROOTFS_SIZE = "512000"

IMAGE_POSTPROCESS_COMMAND += "kosagi_make_rom;"

kosagi_make_rom() {
    true
}
