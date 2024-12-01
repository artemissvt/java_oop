def main():
    """
    Main function that asks user for the time
    and returns what meal time it is
    """
    time = (input('What time is it?'))
    hours = convert(time)
    if 7.0 <= hours <= 8.0:
        print ('Breakfast time.')
    elif 12.0 <= hours <= 13.0:
        print ('Lunch time.')
    elif 18.0 <= hours <= 19.0:
        print ('Dinner time.')
    else:
        print ('No meal.')

def convert(time):
    """
    Funtcion that converts the time the user has given to a float 
    """
    hours, minutes = map(int, time.split (":"))
    return hours + minutes / 60

if __name__ == "__main__":
    main()
