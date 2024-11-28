from alphabets import ALPHABETS
from settings import SHIFT

alphabet_in = input(f"Which alphabet would you like to use ({", ".join(list(ALPHABETS.keys()))}): ")

if alphabet_in not in ALPHABETS:
    raise ValueError("Invalid alphabet selected")

alphabet = ALPHABETS[alphabet_in]
alphabet_length = len(alphabet)

def cesar_encryption(value: str) -> str:
    output = ""
    for let in value:
        try:
            original_index = alphabet.index(let)
        except ValueError:
            print(f"Warning! Missing: {let=} in alphabet!")
            output += " "
            continue
        updated_index = (original_index + SHIFT) % alphabet_length
        output += alphabet[updated_index]
    
    return output

# Python 3.13 syntax! Escape quotes for earlier versions!
print(f"Encrypted: {cesar_encryption(input("Enter text for encryption: "))}")
