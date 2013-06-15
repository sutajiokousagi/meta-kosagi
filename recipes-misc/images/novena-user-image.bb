# Image for assisting in hardware bringup

require systemd-image.bb

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
    make gcc gcc-symlinks g++-symlinks libc6-dev mplayer2 mplayer-common \
    vim vim-vimrc dosfstools e2fsprogs e2fsprogs-badblocks e2fsprogs-e2fsck \
    \
    ${XSERVER} \
    kernel-modules \
"
