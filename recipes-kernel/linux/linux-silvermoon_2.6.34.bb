SECTION = "kernel"
DESCRIPTION = "Linux kernel for Silvermoon platforms"
LICENSE = "GPLv2"
KERNEL_IMAGETYPE = "zImage"

require linux.inc

S = "${WORKDIR}/git"

MULTI_CONFIG_BASE_SUFFIX = ""
BRANCH = "kovan"

COMPATIBLE_MACHINE = "(kovan)"
SRCREV = "fff11d0d6955708500a91798d3199feeddce1fd3"

SRC_URI += "git://github.com/sutajiokousagi/linux.git;branch=${BRANCH} \
	file://defconfig"
