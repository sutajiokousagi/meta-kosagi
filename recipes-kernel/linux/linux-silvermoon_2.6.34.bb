SECTION = "kernel"
DESCRIPTION = "Linux kernel for Silvermoon platforms"
LICENSE = "GPLv2"
KERNEL_IMAGETYPE = "zImage"

require linux.inc

S = "${WORKDIR}/git"

MULTI_CONFIG_BASE_SUFFIX = ""
BRANCH = "kovan"

COMPATIBLE_MACHINE = "(kovan)"
SRCREV = "8b9f0a7b0fb15444a7812b7e1eb043a66ee56478"

SRC_URI += "git://github.com/sutajiokousagi/linux.git;branch=${BRANCH} \
	file://defconfig"
