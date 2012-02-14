inherit native

DESCRIPTION = "Disk image creator"
HOMEPAGE = "http://www.kosagi.com/"
AUTHOR = "Sean Cross"
LICENSE = "BSD"
PR = "r0"

SRC_URI = "git://github.com/sutajiokousagi/makedisk.git;protocol=http"
SRCREV = "32aff0281c9c82909fa6bf49b6c8e165337adeea"
S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} makedisk.c -o makedisk
}

do_install() {
    install -d ${D}/${bindir}
    install -m 0755 ${S}/makedisk ${D}/${bindir}/makedisk
}
