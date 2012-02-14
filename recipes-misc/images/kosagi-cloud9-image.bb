# Image with cloud9 ide and hw tools installed

require hardware-bringup-image.bb

IMAGE_INSTALL += " \
	systemd-analyze \
	cloud9 \
	task-sdk-target \
	vim vim-vimrc \
	procps \
	screen minicom \
	git \
	beaglebone-getting-started bonescript \
	led-config \
	cronie-systemd ntpdate \
	nano \
	minicom \
	hicolor-icon-theme \
	gateone \
	tar \
	gdb gdbserver \
	nodejs-dev \
	mplayer2 \
"

export IMAGE_BASENAME = "Cloud9-IDE"

