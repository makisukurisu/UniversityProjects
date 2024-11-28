KEY = "БАРВНИК"
SECRET = "ТАБУРЕТ КВАДРАТИ ШІСТНАДЦЯТЬ"

def cypher(secret: str, key: str) -> str:
    to_key_table: dict[str, list[str]] = dict()
    table: list[list[str]] = [[] for _ in key]

    for ind, value in enumerate(secret):
        table[ind % len(key)].append(value)
    
    for ind, letter in enumerate(key):
        to_key_table[letter] = table[ind]
    
    resulting_array = []

    for key in sorted(to_key_table):
        resulting_array.extend(to_key_table[key])
    
    return "".join(resulting_array)

def decrypt(encrypted: str, key: str) -> str:
    row_to_letter: dict[str, list[str]] = dict()
    table: list[list[str]] = [[] for _ in key]

    _row_index = 0
    for ind, value in enumerate(encrypted):
        if ind % (len(encrypted) / len(key)) == 0 and ind != 0:
            _row_index += 1
        table[_row_index].append(value)
    
    for ind, letter in enumerate(sorted(key)):
        row_to_letter[letter] = table[ind]
    
    properly_sorted: list[list[str]] = []
    for letter in key:
        properly_sorted.append(row_to_letter[letter])
    
    resulting_array = []
    for x in range(int(len(encrypted) / len(key))):
        for y in range(len(key)):
            resulting_array.append(properly_sorted[y][x])
    
    return "".join(resulting_array)

encrypted_value = cypher(secret=SECRET, key=KEY)
print(f"{encrypted_value=}")

decrypted_value = decrypt(encrypted=encrypted_value, key=KEY)
print(f"{decrypted_value=}")

assert decrypted_value == SECRET