DESCRIPTION = "Kovan utilities"
HOMEPAGE = "http://www.kosagi.com/"
AUTHOR = "bunnie"
LICENSE = "BSD"
PR = "r1"

SRC_URI = "git://github.com/bunnie/kovan-util.git"
SRCREV = "6956658f26c056041d7137d0633796bfeab57dec"
S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"

CNPLATFORM = "unknown"
CNPLATFORM_kovan = "silvermoon"

do_install() {
    install -d ${D}/${sbindir}
#    install -m 0755 ${S}/helpers/kovan-xilinx.rules ${D}/lib/udev/rules.d/45-kovan-xilinx.rules
    install -m 0644 ${S}/helpers/kovan-xilinx.rules ${D}/lib/udev/rules.d/45-kovan-xilinx.rules
}
