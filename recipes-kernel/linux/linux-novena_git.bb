require linux.inc

DESCRIPTION = "Linux kernel for the Novena platform"
COMPATIBLE_MACHINE = "novena"

# Bump MACHINE_KERNEL_PR in the machine config if you update the kernel.
SRCREV = "f59b51fe3d3092c08d7d554ecb40db24011b2ebc"
SRC_URI = "git://github.com/torvalds/linux.git;protocol=git;branch=master \
           file://defconfig \
           file://novena.dts \
	   file://imx6q.dtsi \
"          

LINUX_VERSION ?= "3.6.0"
PR = "r4"
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

do_install_append() {
    cp -f ${WORKDIR}/novena.dts ${S}/arch/arm/boot/dts
    cp -f ${WORKDIR}/imx6q.dtsi ${S}/arch/arm/boot/dts
    echo "Starting do_install with devices [ ${KERNEL_DEVICETREE} ]"
    for DTS_FILE in ${KERNEL_DEVICETREE}; do
        echo "Using DTS file: ${DTS_FILE}"      
        if [ ! -f ${DTS_FILE} ]; then
            echo "Warning: ${DTS_FILE} is not available!"
            continue
        fi
        DTS_BASE_NAME=`basename ${DTS_FILE} | awk -F "." '{print $1}'`
        DTB_NAME=`echo ${KERNEL_IMAGE_BASE_NAME} | sed "s/${MACHINE}/${DTS_BASE_NAME}/g"`
        DTB_SYMLINK_NAME=`echo ${KERNEL_IMAGE_SYMLINK_NAME} | sed "s/${MACHINE}/${DTS_BASE_NAME}/g"`
        echo "Running in directory "`pwd`
        echo dtc -I dts -O dtb ${KERNEL_DEVICETREE_FLAGS} -o ${DTS_BASE_NAME} ${DTS_FILE}
        dtc -I dts -O dtb ${KERNEL_DEVICETREE_FLAGS} -o ${DTS_BASE_NAME} ${DTS_FILE}
        install -m 0644 ${DTS_BASE_NAME} ${D}/boot/devicetree-${DTB_SYMLINK_NAME}.dtb
    done
    echo "Done do_install_append"
}

do_deploy_append() {
    echo "Append for do_deploy start.  Looking through [ ${KERNEL_DEVICETREE} ]"
    DTS_BASE_NAME=novena
    DTB_NAME=`echo ${KERNEL_IMAGE_BASE_NAME} | sed "s/${MACHINE}/${DTS_BASE_NAME}/g"`
    DTB_SYMLINK_NAME=`echo ${KERNEL_IMAGE_SYMLINK_NAME} | sed "s/${MACHINE}/${DTS_BASE_NAME}/g"`
    install -d ${DEPLOYDIR}
    install -m 0644 ${S}/${DTS_BASE_NAME} ${DEPLOYDIR}/${DTB_NAME}.dtb
    cd ${DEPLOYDIR}
    ln -sf ${DTB_NAME}.dtb ${DTB_SYMLINK_NAME}.dtb
}

FILES_${PN} += "/boot"
