DESCRIPTION = "Firmware for ath9k-based USB wifi adaptors"
LICENSE = "unknown"

PR = "r0"

SRC_URI = "file://htc_9271.fw file://LICENCE.atheros_firmware"

LIC_FILES_CHKSUM = "file://LICENCE.atheros_firmware;md5=30a14c7823beedac9fa39c64fdd01a13"

S = "${WORKDIR}"

do_install() {
	install -d ${D}/${base_libdir}/firmware
	install -m 0644 htc_9271.fw ${D}/${base_libdir}/firmware/
}

FILES_${PN} = "${base_libdir}/firmware/"
PACKAGE_ARCH = "all"
