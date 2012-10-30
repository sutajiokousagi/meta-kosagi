SECTION = "kernel"
DESCRIPTION = "Linux kernel for Silvermoon platforms"
LICENSE = "GPLv2"
KERNEL_IMAGETYPE = "zImage"

require linux.inc

S = "${WORKDIR}/git"

MULTI_CONFIG_BASE_SUFFIX = ""
BRANCH = "kovan"

COMPATIBLE_MACHINE = "(kovan)"
SRCREV = "23a798c009eecd3ec6b4b62e10790225b189ef29"

SRC_URI += "git://github.com/sutajiokousagi/linux.git;branch=${BRANCH};protocol=http \
	file://defconfig"
