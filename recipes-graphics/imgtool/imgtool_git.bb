DESCRIPTION = "Simple framebuffer-based image drawing utility"
HOMEPAGE = "http://www.kosagi.com/"
AUTHOR = "Henry Groover"
LICENSE = "BSD"
PR = "r0"

SRC_URI = "git://github.com/sutajiokousagi/imgtool.git"
SRCREV = "e944835b70dcc630aef3fff619daeecd9bb5391a"
S = "${WORKDIR}/git/src"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"
DEPENDS = "giflib libpng jpeg"

CNPLATFORM = "unknown"
CNPLATFORM_kovan = "silvermoon"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} imgtool.c -o imgtool \
           -DCNPLATFORM_${CNPLATFORM} -DCNPLATFORM=\"${CNPLATFORM}\" \
           -lpng -lgif -ljpeg
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 imgtool ${D}${bindir}
}
