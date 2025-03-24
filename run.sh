SRC_DIR="src/main/java"
BIN_DIR="bin"

mkdir -p "$BIN_DIR"

echo "Compiling Java files..."
javac -d "$BIN_DIR" "$SRC_DIR"/com/calculator/*.java "$SRC_DIR"/com/calculator/ui/*.java "$SRC_DIR"/com/calculator/logic/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful. Running the calculator..."
    cd "$BIN_DIR" || exit
    java com.calculator.Main
else
    echo "Compilation failed. Please check your Java files for errors."
    exit 1
fi