SECTION = "kernel"
DESCRIPTION = "Linux kernel for Silvermoon platforms"
LICENSE = "GPLv2"
KERNEL_IMAGETYPE = "zImage"

require linux.inc

S = "${WORKDIR}/git"

MULTI_CONFIG_BASE_SUFFIX = ""
BRANCH = "kovan"

COMPATIBLE_MACHINE = "(kovan)"
SRCREV = "7f406f6dba6c65174f96ef13be3f0a594f5b4c39"

SRC_URI += "git://github.com/sutajiokousagi/linux.git;branch=${BRANCH} \
	file://defconfig"
