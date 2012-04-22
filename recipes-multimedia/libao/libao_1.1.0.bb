inherit autotools pkgconfig
SRC_URI = "http://downloads.xiph.org/releases/ao/libao-1.1.0.tar.gz"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
#S = "${WORKDIR}/git"

SRC_URI[md5sum] = "2b2508c29bc97e4dc218fa162cf883c8"
SRC_URI[sha256sum] = "29de5bb9b1726ba890455ef7e562d877df87811febb0d99ee69164b88c171bd4"
FILES_${PN} += "/usr/lib/ao/plugins-4/*.so"
FILES_${PN}-dbg += "/usr/lib/ao/plugins-4/.debug/*.so"
FILES_${PN}-dev += "/usr/lib/ao/plugins-4/*.la"
