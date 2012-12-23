require u-boot.inc

UBOOT_BINARY = "u-boot.bin"
UBOOT_IMAGE = "u-boot-${MACHINE}-${PV}-${PR}.bin"
UBOOT_SYMLINK = "u-boot-${MACHINE}.bin"

PV = "2009.07"
PR = "r9"

COMPATIBLE_MACHINE = "(kovan)"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb \
                    file://README;beginline=1;endline=22;md5=5ba4218ac89af7846802d0348df3fb90"
SRC_URI = "git://github.com/sutajiokousagi/u-boot-2009.07-silvermoon.git;protocol=git;branch=${MACHINE} \
          "

SRCREV = "19b29f8e728507e1481758e160d5765abaeef365"

LIC_FILES_CHKSUM = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

S = "${WORKDIR}/git/src"
