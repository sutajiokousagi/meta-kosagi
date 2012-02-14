DESCRIPTION = "Config block mananagement program"
HOMEPAGE = "http://www.kosagi.com/"
AUTHOR = "Sean Cross"
LICENSE = "BSD"
PR = "r0"

SRC_URI = "git://github.com/sutajiokousagi/utils.git"
SRCREV = "b8bf6e289bea1be591e174123420266580fe3826"
S = "${WORKDIR}/git/src"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"

do_compile() {
    ${CXX} ${CFLAGS} ${LDFLAGS} config_util.cpp -o config_util
}

do_install() {
    install -d ${D}/${bindir}
    install -m 0755 ${S}/config_util ${D}/${bindir}/config_util
}
