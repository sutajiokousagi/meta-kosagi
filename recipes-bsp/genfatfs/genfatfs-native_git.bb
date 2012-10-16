inherit native

DESCRIPTION = "FAT-based partition generator"
HOMEPAGE = "http://www.kosagi.com/"
AUTHOR = "Sean Cross"
LICENSE = "BSD"
PR = "r1"

SRC_URI = "git://github.com/xobs/genfatfs.git;protocol=http"
SRCREV = "5c9ef0078b379966d5532389b4f332cfabb9ccd0"
S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"

do_install() {
    install -d ${D}/${bindir}
    install -m 0755 ${S}/genfatfs ${D}/${bindir}/genfatfs
}
