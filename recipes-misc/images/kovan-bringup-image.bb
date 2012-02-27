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
	bc \
	strace gdb \
	bash procps \
"
export IMAGE_BASENAME = "hw-bringup"

