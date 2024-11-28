TABLE_HEIGHT = 4
TABLE_WIDTH = 9


ALPHABET_TABLE = [
    ["А", "Б", "В", "Г", "Ґ", "Д", "Е", "Є", "Ж"],
    ["З", "И", "І", "Ї", "Й", "К", "Л", "М", "Н"],
    ["О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц"],
    ["Ч", "Ш", "Щ", "Ь", "Ю", "Я", " ", ".", "!"],
]

ALPHABET = ""
for row in ALPHABET_TABLE:
    for letter in row:
        ALPHABET += letter

USED_LETTERS = []

KEY = "ЛЯГУШКА"

ENCRYPTION_TABLE = [[] for _ in range(TABLE_HEIGHT)]

_y = 0
for index, letter in enumerate(KEY):
    _x = len(ENCRYPTION_TABLE[_y]) % TABLE_WIDTH
    if index != 0 and _x == 0:
        _y += 1

    ENCRYPTION_TABLE[_y].append(letter)
    USED_LETTERS.append(letter)

REMAINING_LETTERS = list(ALPHABET)

for letter in USED_LETTERS:
    REMAINING_LETTERS.remove(letter)

for letter in REMAINING_LETTERS:
    _x = len(ENCRYPTION_TABLE[_y]) % TABLE_WIDTH
    if index != 0 and _x == 0:
        _y += 1

    ENCRYPTION_TABLE[_y].append(letter)

# Resulting table:
# [
#     ["Л", "Я", "Г", "У", "Ш", "К", "А", "Б", "В"],
#     ["Ґ", "Д", "Е", "Є", "Ж", "З", "И", "І", "Ї"],
#     ["Й", "М", "Н", "О", "П", "Р", "С", "Т", "Ф"],
#     ["Х", "Ц", "Ч", "Щ", "Ь", "Ю", " ", ".", "!"],
# ]

CYPHER_TEXT = "ПОТУЖНА БАВОВНА!"


def get_coordinates(letter) -> tuple[int, int]:
    """
    Returns column and row numbers.
    Position of the letter in the ENCRYPTION_TABLE
    """
    for y, row in enumerate(ENCRYPTION_TABLE):
        for x, cell in enumerate(row):
            if letter == cell:
                return x, y

    raise RuntimeError("Invalid character")


def get_letter(coordinates: tuple[int, int]) -> str:
    x, y = coordinates
    return ENCRYPTION_TABLE[y][x]


def get_other_corners(
    coord_l_1: tuple[int, int],
    coord_l_2: tuple[int, int],
    change: int = 1,
) -> tuple[tuple[int, int], tuple[int, int]]:
    l_1_x, l_1_y = coord_l_1
    l_2_x, l_2_y = coord_l_2

    if l_1_x == l_2_x:
        l_1_x = l_2_x = (l_1_x + change) % TABLE_WIDTH
    if l_1_y == l_2_y:
        l_1_y = l_2_y = (l_2_y + change) % TABLE_HEIGHT

    coord_l_3 = l_1_x, l_2_y
    coord_l_4 = l_2_x, l_1_y

    return coord_l_3, coord_l_4


def encrypt(text: str) -> str:
    bigrams = [[text[i : i + 2]] for i in range(0, len(text), 2)]
    encrypted_bigrams = []

    for bigram in bigrams:
        letter_1, letter_2 = tuple(*bigram)

        letter_1_coord, letter_2_coord = (
            get_coordinates(letter_1),
            get_coordinates(letter_2),
        )
        letter_3_coord, letter_4_coord = get_other_corners(
            coord_l_1=letter_1_coord,
            coord_l_2=letter_2_coord,
        )
        letter_3, letter_4 = get_letter(letter_3_coord), get_letter(letter_4_coord)

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
        letter_1, letter_2 = tuple(*bigram)

        letter_1_coord, letter_2_coord = (
            get_coordinates(letter_1),
            get_coordinates(letter_2),
        )
        letter_3_coord, letter_4_coord = get_other_corners(
            coord_l_1=letter_1_coord,
            coord_l_2=letter_2_coord,
            change=-1,
        )
        letter_3, letter_4 = get_letter(letter_3_coord), get_letter(letter_4_coord)

        decrypted_bigrams.append([letter_3, letter_4])

    flat_bigrams = []
    for bigram in decrypted_bigrams:
        flat_bigrams.extend(bigram)

    return "".join(flat_bigrams)


encrypted_text = encrypt(CYPHER_TEXT)
print(f"Encrypted text: {encrypted_text}")
# ЬЩБОПЕЩУІИФУФГ В

decrypted_text = decrypt(encrypted_text)
print(f"Decrypted text: {decrypted_text}")

assert decrypted_text == CYPHER_TEXT
