require conf/machine/include/tune-cortexa9.inc

PREFERRED_PROVIDER_virtual/bootloader = "u-boot-imx"
PREFERRED_PROVIDER_virtual/kernel = "linux-novena"

# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r0"
KERNEL_IMAGETYPE = "uImage"
KERNEL_ALT_IMAGETYPE = "LOADADDR=0x10008000"

EXTRA_IMAGEDEPENDS += "u-boot-imx"
UBOOT_MACHINE = "mx6qsabrelite_config"
UBOOT_SUFFIX = "imx"
UBOOT_MAKE_TARGET = "u-boot.imx"

IMAGE_ROOTFS_SIZE = "512000"

IMAGE_POSTPROCESS_COMMAND += "novena_make_rom;"

novena_make_rom() {
    true
}
