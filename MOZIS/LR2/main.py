# main.py
from settings import TABLE_1, TABLE_2


def _get_index(table: list[list[str]], value: str) -> tuple[int, int]:
    for i, row in enumerate(table):
        if value not in row:
            continue
        return i, row.index(value)
    raise ValueError(f"{value} not found in {table=}")


def transform_indexes(
    table: list[list[str]],
    data: list[tuple[int, int]],
    modifier: int = 1,
) -> list[tuple[int, int]]:
    # Adding 1 for encryption
    # Subtracting 1 for decryption (changing row to which it points)
    size = len(table)
    result = []

    for index in data:
        result.append(
            (
                (index[0] + modifier) % size,
                index[1],
            )
        )

    return result


def polybius_encrypt(value: str) -> str:
    original_indexes = [_get_index(TABLE_1, i) for i in value]
    encrypted_indexes = transform_indexes(TABLE_2, data=original_indexes)

    return "".join([TABLE_2[x][y] for x, y in encrypted_indexes])


def polybius_decrypt(value: str) -> str:
    encrypted_indexes = [_get_index(TABLE_2, i) for i in value]
    decrypted_indexes = transform_indexes(
        TABLE_1,
        data=encrypted_indexes,
        modifier=-1,
    )

    return "".join([TABLE_1[x][y] for x, y in decrypted_indexes])


print(polybius_encrypt("SOMETEXT"))
# Outputs: NPO?Z?/Z
print(polybius_decrypt("NPO?Z?/Z"))
# Outputs: SOMETEXT
