# runqemu.sh boots the image with "nographic", so QEMU's graphical display
# backends are not needed. Dropping them avoids building mesa-native and
# llvm-native, which add hours to the first build.
PACKAGECONFIG:remove = "virglrenderer epoxy sdl"
