DESCRIPTION = "SOC register mananagement program"
HOMEPAGE = "http://www.kosagi.com/"
AUTHOR = "Sean Cross"
LICENSE = "BSD"
PR = "r0"

SRC_URI = "git://github.com/sutajiokousagi/utils.git"
SRCREV = "b8bf6e289bea1be591e174123420266580fe3826"
S = "${WORKDIR}/git/src"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"

CNPLATFORM = "unknown"
CNPLATFORM_kovan = "silvermoon"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} regutil.c -o regutil
}

do_install() {
    install -d ${D}/${sbindir}
    install -m 0755 ${S}/regutil ${D}/${sbindir}/regutil
}
