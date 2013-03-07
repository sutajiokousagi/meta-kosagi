# Flip on USB power
i2c dev 1
i2c mw 08 66 48

# Set CPU voltage to 1.2V (rather than the default of 1.375V)
i2c mw 08 20 26

mmc dev 1
if fatload mmc 1 0x12000000 uImage-novena.bin
then
	fatload mmc 1 0x11ff0000 uImage-novena.dtb
	setenv bootargs console=${console},${baudrate} root=${mmcroot}
	setenv bootargs console=ttymxc1,115200 earlyprintk root=/dev/mmcblk1p2 rootwait rootfstype=ext4
else
	mmc dev 0
	fatload mmc 0 0x12000000 uImage-novena.bin
	fatload mmc 0 0x11ff0000 uImage-novena.dtb
	setenv bootargs console=ttymxc1,115200 earlyprintk root=/dev/mmcblk0p2 rootwait rootfstype=ext4
fi
bootm 0x12000000 - 0x11ff0000
