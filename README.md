# Enigma-de-Caeser

A small Java command-line project that demonstrates a custom Caesar-style cipher combined with salting and a plugboard-like substitution. It attempts to recover a plaintext password by brute force, given a SHA-256 hash, a plugboard mapping, and a wordlist.

## How It Works
For each word in a wordlist, the program:
1. Generates salted variants by prepending/appending two-character salts.
2. Applies a plugboard substitution.
3. Applies a Caesar-like cipher with rotation and incremental shifting per character.
4. Applies the plugboard again.
5. Hashes each candidate with SHA-256 and compares to the target hash.

When a match is found, it prints the password, the salt, and the cipher parameters (rotation and increment).

## Files
- `BreakingEnigma.java` Main entry point and brute-force loop
- `Salter.java` Wordlist loading, salt generation, and SHA-256 hashing
- `Plugboard.java` Plugboard substitution logic
- `Caesar.java` Caesar-style cipher with rotation + per-character increment

## Usage
Compile:

```bash
javac *.java
```

Run:

```bash
java BreakingEnigma <sha256_hash> "{A:B, C:D}" <wordlist.txt>
```

Example:

```bash
java BreakingEnigma 0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef "{A:B, B:C, C:D}" wordlist.txt
```

## Input Requirements
- `sha256_hash` must be a 64-character hex string.
- `plugboard` must be in the format `{A:B, C:D}`.
- `wordlist.txt` must be a `.txt` file with one word per line.

## Notes
- The Caesar cipher uses `rotation` and `increment` (both 0–25). The increment is multiplied by the character index.
- The algorithm is intentionally brute-force and can be slow for large wordlists.

## License
Not specified.
