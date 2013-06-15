DESCRIPTION = "Firmware for i.MX6 SDMA core"
LICENSE = "Proprietary"

PR = "r0"

SRC_URI = "file://sdma-imx6q.bin"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/${base_libdir}/firmware/imx/sdma
    install -m 0644 sdma-imx6q.bin ${D}/${base_libdir}/firmware/imx/sdma
}

FILES_${PN} = "${base_libdir}/firmware/"
PACKAGE_ARCH = "all"
