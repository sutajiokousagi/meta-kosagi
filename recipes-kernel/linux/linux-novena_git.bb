require linux.inc

DESCRIPTION = "Linux kernel for the Novena platform"
COMPATIBLE_MACHINE = "novena"

# Bump MACHINE_KERNEL_PR in the machine config if you update the kernel.
SRCREV = "f59b51fe3d3092c08d7d554ecb40db24011b2ebc"
SRC_URI = "git://github.com/torvalds/linux.git;protocol=git;branch=master \
           file://defconfig \
"          

LINUX_VERSION ?= "3.6.0"
PR = "r0"
PV = "${LINUX_VERSION}+${PR}+git"

S = "${WORKDIR}/git"

KERNEL_DEFCONFIG = "novena_defconfig"

PARALLEL_MAKEINST = ""


# Override the do_compile from kernel.bbclass, because we don't have
# a version.h target for some reason.
kernel_do_compile() {  
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE  
    oe_runmake ${KERNEL_IMAGETYPE_FOR_MAKE} ${KERNEL_ALT_IMAGETYPE} CC="${KERNEL_CC}" LD="${KERNEL_LD}"  
    if test "${KERNEL_IMAGETYPE_FOR_MAKE}.gz" = "${KERNEL_IMAGETYPE}"; then  
        gzip -9c < "${KERNEL_IMAGETYPE_FOR_MAKE}" > "${KERNEL_OUTPUT}"  
    fi  
}  
