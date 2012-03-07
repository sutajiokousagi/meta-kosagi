SECTION = "kernel"
DESCRIPTION = "Linux kernel for Silvermoon platforms"
LICENSE = "GPLv2"
KERNEL_IMAGETYPE = "zImage"

require linux.inc

S = "${WORKDIR}/git"

MULTI_CONFIG_BASE_SUFFIX = ""
BRANCH = "kovan"

COMPATIBLE_MACHINE = "(kovan)"
SRCREV = "67b459d7c33a6a887963c9bbefec45980a523523"

SRC_URI += "git://github.com/sutajiokousagi/linux.git;branch=${BRANCH} \
	file://defconfig"
