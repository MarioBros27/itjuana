import re


data = {
    "shipments": [
        "215 Osinski Manors",
        "9856 Marvin Stravenue",
        "7127 Kathlyn Ferry",
        "987 Champlin Lake",
        "63187 Volkman Garden Suite 447",
        "75855 Dessie Lights",
        "1797 Adolf Island Apt. 744",
        "2431 Lindgren Corners",
        "8725 Aufderhar River Suite 859",
        "79035 Shanna Light Apt. 322"
    ],
    "drivers": [
        "Everardo Welch",
        "Orval Mayert",
        "Howard Emmerich",
        "Izaiah Lowe",
        "Monica Hermann",
        "Ellis Wisozk",
        "Noemie Murphy",
        "Cleve Durgan",
        "Murphy Mosciski",
        "Kaiser Sose"
    ]
}
driver = {
    "name",
    "name_length",
    "shipment_address",
    "factors"  # [int],
    "bonus_winner"  # bool
}
address = {
    "address",
    "street_length",
    "factors"  # [int]
}


class Driver:
    def __init__(self, name, name_length, shipment_address, factors):
        self.name = name
        self.name_length = name_length
        self.shipment_address = shipment_address
        self.factors = factors
        self.ss = 0


class Address:
    def __init__(self, address, street_length, factors):
        self.address = address
        self.street_length = street_length
        self.factors = factors


def get_name_len(name):
    matching_results = re.findall("[a-zA-Z\.]+", name)
    if len(matching_results) == 0:
        raise Exception("did not find only one match for: ", name)

    total = 0
    for word in matching_results:
        total += len(word)
    return total


def calculate_factors(n):
    factors = []
    i = n
    while(i > 1):
        if n % i == 0:
            factors.append(i)
        i -= 1
    return factors

def main():
    addresses_data = data["shipments"]
    names_data = data["drivers"]
    drivers = []
    addresses = []
    for name in addresses_data:
        length = get_name_len(name)
        factors = calculate_factors(length)
        addresses.append(Address(name, length, factors))
    for name in names_data:
        length = get_name_len(name)
        factors = calculate_factors(length)
        drivers.append(Driver(name, length,None,factors))
    matrix = [[0]*len(drivers)]*len(addresses)
    #Filling matrix with SS
    for i in range(len(addresses)):
        for j in range(len(drivers)):
            address = addresses[i]
            driver = drivers[j]
            ss = 0
            #Requirement one and two
            if address.street_length % 2 == 0:
                ss = driver.name_length * 1.5
            else:
                ss = driver.name_length
            #Requirement three
            for address_factor in address.factors:
                if address_factor in driver.factors:
                    ss = ss * 1.5
                    break
            matrix[i][j] = ss
        print(matrix[i])
    #Finding optimal combination. Using Hungarian algorithm


main()

# First fetch the data and assign them to lists objects. O(n)
# Calculate length of address and name and store them in each object. O(n)
# Calculate factors and store them for each object. O(n)
# Build a matrix where driverXaddress stores the ss value including the bonus for when common factors
