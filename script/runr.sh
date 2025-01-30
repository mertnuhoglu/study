# [[runr.sh]]

# spcs: Call R script via command line from Lua `prg/lua prg/rlang nlys/spcs` || ((f205725d-e824-4e78-8fa5-687b1d6bdbbd))
# src: [[20241124-runr--locate-8-run-R-script-03.sh]]

# Usage:
# runr.sh --script=20241124-named-arguments-in-command-line-02.R --input=20241124-exmp-input.tsv --input2=input2.tsv --input3=input3.tsv
# runr.sh --script=20241124-named-arguments-in-command-line-02.R --input=20241124-exmp-input.tsv --input2=input2.tsv --input3=input3.tsv --output=20241124-exmp-input.tsv --output2=input2.tsv --output3=input3.tsv
#
# Prerequisites:
# redis-cli SET path:20241124-named-arguments-in-command-line-02.R '/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241124-call-R-script-from-Lua/20241124-named-arguments-in-command-line-02.R'
# redis-cli SET path:20241124-exmp-input.tsv '...'

echo "runr.sh"

if [ $# -eq 0 ]; then
    echo "Error: No arguments provided"
    exit 1
fi

script=""
input=""
input2=""
input3=""
output=""
output2=""
output3=""

# Parse options manually
while [ "$#" -gt 0 ]; do
    case "$1" in
        --script=*)
            script="${1#*=}"
            ;;
        --input=*)
            input="${1#*=}"
            ;;
        --input2=*)
            input2="${1#*=}"
            ;;
        --input3=*)
            input3="${1#*=}"
            ;;
        --output=*)
            output="${1#*=}"
            ;;
        --output2=*)
            output2="${1#*=}"
            ;;
        --output3=*)
            output3="${1#*=}"
            ;;
        *)
            echo "Unknown parameter: $1"
            echo "Usage: $0 --input=file.txt --output=result.txt"
            exit 1
            ;;
    esac
    shift
done

# Validate required arguments
if [ -z "$script" ]; then
    echo "Error: --script is required"
    echo "Usage: $0 --script=file.txt [--output=output.txt]"
    exit 1
fi

if [ -z "$input" ]; then
    echo "Error: --input is required"
    echo "Usage: $0 --input=file.txt [--output=output.txt]"
    exit 1
fi

# Use the arguments
echo "script: $script"
echo "input: $input"
echo "input2: $input2"
echo "input3: $input3"
echo "Output: $output"
echo "Output2: $output2"
echo "Output3: $output3"

key_script=$(printf '%q' "$script")
key_input=$(printf '%q' "$input")
key_input2=$(printf '%q' "$input2")
key_input3=$(printf '%q' "$input3")
key_output=$(printf '%q' "$output")
key_output2=$(printf '%q' "$output2")
key_output3=$(printf '%q' "$output3")

script_path=$(redis-cli GET "path:$key_script")
input_path=$(redis-cli GET "path:$key_input")
echo "script_path: $script_path"
echo "input_path: $input_path"

if ! [ -z "$input2" ]; then
	input2_path=$(redis-cli GET "path:$key_input2")
	echo "input2_path: $input2_path"
fi
if ! [ -z "$input3" ]; then
	input3_path=$(redis-cli GET "path:$key_input3")
	echo "input3_path: $input3_path"
fi
if ! [ -z "$output" ]; then
	output_path=$(redis-cli GET "path:$key_output")
	echo "output_path: $output_path"
fi
if ! [ -z "$output2" ]; then
	output2_path=$(redis-cli GET "path:$key_output2")
	echo "output2_path: $output2_path"
fi
if ! [ -z "$output3" ]; then
	output3_path=$(redis-cli GET "path:$key_output3")
	echo "output3_path: $output3_path"
fi

if [ -z "$script_path" ]; then
		echo "Error: Path not found in Redis"
		exit 1
fi
if [ -z "$input_path" ]; then
		echo "Error: Path not found in Redis"
		exit 1
fi

if [ ! -f "$script_path" ]; then
    echo "Error: Script file not found: $script_path"
    exit 1
fi
if [ ! -f "$input_path" ]; then
    echo "Error: input file not found: $input_path"
    exit 1
fi

echo "Rscript \"${script_path}\" \
    --input=\"${input_path}\" \
    --input2=\"${input2_path}\" \
    --input3=\"${input3_path}\" \
    --output=\"${output_path}\" \
    --output2=\"${output2_path}\" \
    --output3=\"${output3_path}\""

Rscript "${script_path}" \
    --input="${input_path}" \
    --input2="${input2_path}" \
    --input3="${input3_path}" \
    --output="${output_path}" \
    --output2="${output2_path}" \
    --output3="${output3_path}"

