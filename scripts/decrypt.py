#!/bin/python3
import argparse
from pathlib import Path

parser = argparse.ArgumentParser(
    description="Oringo jar decryptor"
)
parser.add_argument(
    "-i", "--input",
    type=Path,
    help="Path to the input jar",
    required=True
)
parser.add_argument(
    "-o", "--output",
    type=Path,
    help="Path to output jar",
    required=True
)
parser.add_argument(
    "-c", "--code",
    type=int,
    help="The solved_code for this jar",
    required=True
)
args = parser.parse_args()

running_key = [0x00, 0xFF, 0xFF, 0xFE, 0xFA, 0xE8, 0x88, 0x30, 0x50, 0x80, 0x80]

with open(args.input, "rb") as f:
	data = f.read()

decrypted = []
for index, byte in enumerate(data):
	transformed_byte = (byte - (-args.code * index)) % 256

	decrypted_byte = transformed_byte - running_key[index % len(running_key)]
	if decrypted_byte < 0:
		decrypted_byte += 256

	decrypted.append(decrypted_byte)

with open(args.output, "wb") as f:
	f.write(bytes(decrypted))
