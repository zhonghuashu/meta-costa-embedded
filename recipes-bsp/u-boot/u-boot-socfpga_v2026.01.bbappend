# Point the U-Boot source fetch to the local u-boot-socfpga mirror.
# This keeps the upstream recipe version and branch, but overrides the repo URL.
UBOOT_REPO = "git:///home/shu/github/u-boot-socfpga-2026.01"
UBOOT_PROT = "file"

SRCREV = "6e59447316d06b25ca98caaa5c16787f5c74e862"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://socfpga_sockit_sdcard.cfg"
