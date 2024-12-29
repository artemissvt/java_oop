import requests

def suggest_random_activity():
    url = "https://bored-api.appbrewery.com/random"
    try:
        response = requests.get(url)
        response.raise_for_status()
        data = response.json()
        activity = data.get("activity", "No activity found.")
        print(f"Here's a suggestion for you: {activity}")
    
    except requests.exceptions.RequestException as e:
        print(f"Error fetching data from the Bored API: {e}")

if __name__ == "__main__":
    suggest_random_activity()
