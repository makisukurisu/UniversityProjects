import random
from typing import Literal

ALHPABET = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ .!"


# For table generation
def scramble_alphabet() -> list[str]:
    shuffle = list(ALHPABET)
    random.shuffle(shuffle)
    return shuffle


def scrambled_table() -> list[list[str]]:
    alphabet = scramble_alphabet()

    return [alphabet[i * 6 : (i + 1) * 6] for i in range(6)]


TABLE_1 = [
    [" ", "Д", "К", "Н", "Ї", "А"],
    ["!", "О", "Ш", "Ч", "Ґ", "Х"],
    ["Є", "Я", "П", "Г", "М", "И"],
    ["У", "Ц", "Е", "Л", "Ф", "Ж"],
    ["І", "Р", "Т", ".", "Ю", "В"],
    ["Й", "С", "Щ", "Ь", "Б", "З"],
]
TABLE_2 = [
    ["О", "І", "И", "Ї", "Р", "Ч"],
    ["Щ", "С", "!", " ", "А", "К"],
    ["У", "Г", "Ю", "Л", "Д", "Я"],
    [".", "М", "В", "Т", "Ш", "Ф"],
    ["Н", "З", "Ґ", "Ж", "П", "Є"],
    ["Х", "Е", "Ц", "Б", "Ь", "Й"],
]

CYPHER_TEXT = "КЕЙТЛІН НЕ ГОВОРИТЬ ФРАНЦУЗЬКОЮ!"


def get_coordinates(
    letter,
    table_name: Literal["LEFT", "RIGHT"] = "LEFT",
) -> tuple[int, int]:
    """
    Returns column and row numbers.
    Position of the letter in the `table`
    """
    table = TABLE_1 if table_name == "LEFT" else TABLE_2

    for y, row in enumerate(table):
        for x, cell in enumerate(row):
            if letter == cell:
                return x, y

    raise RuntimeError("Invalid character")


def get_letter(
    coordinates: tuple[int, int],
    table_name: Literal["LEFT", "RIGHT"] = "LEFT",
) -> str:
    table = TABLE_1 if table_name == "LEFT" else TABLE_2

    x, y = coordinates
    return table[y][x]


def get_new_letters_decrypt(
    l2_coords: tuple[int],
    l1_coords: tuple[int],
) -> tuple[str]:
    l2_x, l2_y = l2_coords
    l1_x, l1_y = l1_coords

    return TABLE_2[l2_y][l1_x], TABLE_1[l1_y][l2_x]


def get_new_letters(
    l1_coords: tuple[int, int],
    l2_coords: tuple[int, int],
) -> tuple[str]:
    l1_x, l1_y = l1_coords
    l2_x, l2_y = l2_coords

    return TABLE_2[l2_y][l1_x], TABLE_1[l1_y][l2_x]


def encrypt(secret: str) -> str:
    bigrams = [[secret[i : i + 2]] for i in range(0, len(secret), 2)]
    encrypted_bigrams = []

    for bigram in bigrams:
        letter_1, letter_2 = tuple(*bigram)
    
        letter_1_coord = get_coordinates(letter_1, "LEFT")
        letter_2_coord = get_coordinates(letter_2, "RIGHT")
    
        letter_3, letter_4 = get_new_letters(
            letter_1_coord,
            letter_2_coord,
        )
    
        encrypted_bigrams.append([letter_3, letter_4])
    

    flat_bigrams = []
    for bigram in encrypted_bigrams:
        flat_bigrams.extend(bigram)

    return "".join(flat_bigrams)


def decrypt(encrypted_text: str) -> str:
    encrypted_bigrams = [
        [encrypted_text[i : i + 2]] for i in range(0, len(encrypted_text), 2)
    ]
    decrypted_bigrams = []

    for bigram in encrypted_bigrams:
        # Reversing order for decryption
        letter_1, letter_2 = tuple(*bigram)

        letter_1_coord = get_coordinates(letter_1, "RIGHT")
        letter_2_coord = get_coordinates(letter_2, "LEFT")

        letter_4, letter_3 = get_new_letters_decrypt(
            letter_1_coord,
            letter_2_coord,
        )
    
        decrypted_bigrams.append([letter_3, letter_4])

    flat_bigrams = []
    for bigram in decrypted_bigrams:
        flat_bigrams.extend(bigram)

    return "".join(flat_bigrams)

encrypted_text = encrypt(CYPHER_TEXT)
print(f"Encrypted text: {encrypted_text}")

decrypted_text = decrypt(encrypted_text)
print(f"Decrypted text: {decrypted_text}")

assert decrypted_text == CYPHER_TEXT