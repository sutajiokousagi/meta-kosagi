# Image for assisting in hardware bringup

#Angstrom image to test systemd

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

IMAGE_PREPROCESS_COMMAND = "rootfs_update_timestamp"

DISTRO_UPDATE_ALTERNATIVES ??= ""
ROOTFS_PKGMANAGE_PKGS ?= '${@base_conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "${ROOTFS_PKGMANAGE} ${DISTRO_UPDATE_ALTERNATIVES}", d)}'

IMAGE_INSTALL += " \
        angstrom-task-boot \
        task-basic \
        ${ROOTFS_PKGMANAGE_PKGS} \
        timestamp-service \
"

IMAGE_DEV_MANAGER   = "udev"
IMAGE_INIT_MANAGER  = "systemd"
IMAGE_INITSCRIPTS   = " "
IMAGE_LOGIN_MANAGER = "tinylogin shadow"

inherit image

IMAGE_INSTALL += " \
    usbutils \
    i2c-tools \
    alsa-utils \
    pciutils \
    wireless-tools ath9k-firmware \
    devmem2 \
    iw \
    bonnie++ \
    hdparm \
    iozone3 \
    iperf \
    lmbench \
    rt-tests \
    evtest \
    udev \ 
    systemd-analyze \
    strace gdb lsof bc bash procps kexec \
    openssh openssh-scp openssh-ssh \
    python-fcntl python-subprocess python-ctypes python-terminal \
    libicui18n xorg-minimal-fonts liberation-fonts \
    task-native-sdk \
    git-dev \
    \
    ${XSERVER} \
    kernel-modules \
    \
    tor tor-masquerade netbase-tor \
    mplayer2 sdma-firmware \
    ntpdate \
"
