require u-boot.inc

COMPATIBLE_MACHINE = "(novena)"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb \
                    file://README;beginline=1;endline=22;md5=78b195c11cb6ef63e6985140db7d7bab"

SRCREV = "34275d70fec6cc369a931090ebb686bc213bb80d"

PV = "v2012.10+git${SRCPV}"
PR = "r2"

SRC_URI = "git://github.com/sutajiokousagi/u-boot-imx6.git;branch=u-boot-imx;protocol=git\
           file://novena.h \
           file://boot.script \
"

S = "${WORKDIR}/git"

do_configure_prepend() {
    cp ${WORKDIR}/novena.h ${S}/include/configs/mx6qsabrelite.h
}

do_compile_append() {
    mkimage -A arm \
            -O linux \
            -a 0 \
            -e 0 \
            -T script \
            -C none \
            -n "Boot script" \
            -d ${WORKDIR}/boot.script \
               ${S}/boot.scr
}

do_install_append() {
    install ${S}/boot.scr ${D}/boot/boot.scr
}

do_deploy_append() {
    install ${S}/boot.scr ${DEPLOYDIR}/boot.scr
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
