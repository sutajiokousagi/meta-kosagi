require linux.inc

DESCRIPTION = "Linux kernel for the Novena platform"
COMPATIBLE_MACHINE = "novena"

# Bump MACHINE_KERNEL_PR in the machine config if you update the kernel.
SRCREV = "276f0847c9356db7b58ec2357c5fcbcf5b017bd3"
SRC_URI = "git://github.com/sutajiokousagi/linux.git;protocol=git;branch=novena \
           file://defconfig \
           file://imx6q-novena.dts \
"          

LINUX_VERSION ?= "3.6.0"
PR = "r7"
PV = "${LINUX_VERSION}+${PR}+git"

S = "${WORKDIR}/git"

KERNEL_DEFCONFIG = "novena_defconfig"

PARALLEL_MAKEINST = ""


# Override the do_compile from kernel.bbclass, because we don't have
# a version.h target for some reason.
kernel_do_compile() {  
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE  
    oe_runmake ${KERNEL_IMAGETYPE_FOR_MAKE} LOADADDR="${UBOOT_ENTRYPOINT}" CC="${KERNEL_CC}" LD="${KERNEL_LD}"  
    if test "${KERNEL_IMAGETYPE_FOR_MAKE}.gz" = "${KERNEL_IMAGETYPE}"; then  
        gzip -9c < "${KERNEL_IMAGETYPE_FOR_MAKE}" > "${KERNEL_OUTPUT}"  
    fi
}  

do_install_prepend() {
# Remove duplicates from PATH, which grows large and unweildy.
# Code ganked from http://unix.stackexchange.com/questions/40749/remove-duplicate-path-entries-with-awk-command
    set -f         # Turn off globbing, to allow unprotected variable substitutions
    IFS=:
    old_PATH=$PATH:; PATH=
    while [ -n "$old_PATH" ]; do
      x=${old_PATH%%:*}       # the first remaining entry
      case $PATH: in
        *:${x}:*) :;;         # already there
        *) PATH=$PATH:$x;;    # not there yet
      esac
      old_PATH=${old_PATH#*:}
    done
    export PATH=${PATH#:}
    set +f; unset IFS old_PATH x
}

do_install_append() {
    cp -f ${WORKDIR}/imx6q-novena.dts ${S}/arch/arm/boot/dts
    make dtbs
    install -m 0644 ${DTS_BASE_NAME} ${D}/boot/devicetree-${DTB_SYMLINK_NAME}.dtb
}

do_deploy_append() {
    echo "Append for do_deploy start.  Looking through [ ${KERNEL_DEVICETREE} ]"
    DTS_BASE_NAME=imx6q-novena
    DTB_NAME=`echo ${KERNEL_IMAGE_BASE_NAME} | sed "s/${MACHINE}/${DTS_BASE_NAME}/g"`
    DTB_SYMLINK_NAME=uImage-novena.dtb
    install -d ${DEPLOYDIR}
    install -m 0644 ${S}/${DTS_BASE_NAME} ${DEPLOYDIR}/${DTB_NAME}.dtb
    cd ${DEPLOYDIR}
    ln -sf ${DTB_NAME}.dtb ${DTB_SYMLINK_NAME}
}

FILES_${PN} += "/boot"
