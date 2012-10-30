inherit native

DESCRIPTION = "Disk image creator"
HOMEPAGE = "http://www.kosagi.com/"
AUTHOR = "Sean Cross"
LICENSE = "BSD"
PR = "r1"

SRC_URI = "git://github.com/sutajiokousagi/makedisk.git"
SRCREV = "c343d8e8176482cf2302ea5d8e1974e9178eab83"
S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"

do_install() {
    install -d ${D}/${bindir}
    install -m 0755 ${S}/makedisk ${D}/${bindir}/makedisk
}
