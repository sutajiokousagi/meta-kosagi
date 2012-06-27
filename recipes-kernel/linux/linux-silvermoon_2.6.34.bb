SECTION = "kernel"
DESCRIPTION = "Linux kernel for Silvermoon platforms"
LICENSE = "GPLv2"
KERNEL_IMAGETYPE = "zImage"

require linux.inc

S = "${WORKDIR}/git"

MULTI_CONFIG_BASE_SUFFIX = ""
BRANCH = "kovan"

COMPATIBLE_MACHINE = "(kovan)"
SRCREV = "f2881ac8089f2d7145568610c53728a3cd49a43a"

SRC_URI += "git://github.com/sutajiokousagi/linux.git;branch=${BRANCH} \
	file://defconfig"
