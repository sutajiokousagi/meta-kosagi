require u-boot.inc

# To build u-boot for your machine, provide the following lines in your machine
# config, replacing the assignments as appropriate for your machine.
# UBOOT_MACHINE = "omap3_beagle_config"
# UBOOT_ENTRYPOINT = "0x80008000"
# UBOOT_LOADADDRESS = "0x80008000"

COMPATIBLE_MACHINE = "(novena)"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb \
                    file://README;beginline=1;endline=22;md5=78b195c11cb6ef63e6985140db7d7bab"

# This revision corresponds to the tag "v2012.10"
SRCREV = "0eddf9613b4ac89f67f1d13ec831823013220007"

PV = "v2012.10+git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/Freescale/u-boot-imx.git;branch=patches-2012.10;protocol=git"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
