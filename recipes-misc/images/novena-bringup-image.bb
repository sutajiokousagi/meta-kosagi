# Image for assisting in hardware bringup

require systemd-image.bb

IMAGE_INSTALL += " \
    usbutils \
    i2c-tools \
    alsa-utils \
    devmem2 \
    iw \
    bonnie++ \
    hdparm \
    iozone3 \
    iperf \
    lmbench \
    rt-tests \
    evtest \
    systemd-analyze \
    strace gdb lsof bc bash procps kexec \
    openssh openssh-scp openssh-ssh \
    udev \
    python-fcntl python-subprocess python-ctypes python-terminal \
    libicui18n xorg-minimal-fonts liberation-fonts \
    \
    ${XSERVER} \
    kernel-modules \
"
export IMAGE_BASENAME = "hw-bringup"

