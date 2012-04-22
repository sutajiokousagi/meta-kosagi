SRC_URI = "git://github.com/albertz/shairport.git"
SRCREV = "58a6ab8818ea1ee63554a826202beadd652b447c"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ed539e41bdcf2568e838ae58205dc02d"
S = "${WORKDIR}/git"
DEPENDS = "libao"
RDEPENDS_${PN} += "libao"
CFLAGS += "-O2 -Wall `pkg-config --cflags openssl ao`"
LDFLAGS += "-lm -lpthread `pkg-config --libs openssl ao`"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 shairport ${D}/usr/bin
	install -m 0755 shairport.pl ${D}/usr/bin
	install -m 0755 hairtunes ${D}/usr/bin
}

FILES_${PN} += "/usr/bin"
