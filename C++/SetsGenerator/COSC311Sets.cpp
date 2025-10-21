// COSC311Sets.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Set {
private:
    vector<string> elements;  // Supports integers, characters, and strings
public:
    Set(vector<string> elems) { elements = elems; }

    void display() {
        cout << "{ ";
        for (size_t i = 0; i < elements.size(); i++) {
            cout << elements[i];
            if (i != elements.size() - 1) cout << ", ";
        }
        cout << " }" << endl;
    }

    vector<string> getElements() { return elements; }

    
    // **Union** (A U B)
    Set unionWith(Set other) {
        vector<string> result = elements;

        // Add elements from 'other' that are not already in 'result'
        for (string elem : other.getElements()) {
            bool found = false;
            for (string existing : result) {
                if (elem == existing) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.push_back(elem);
            }
        }
        return Set(result);
    }

    // **Intersection** (A n B)
    Set intersectionWith(Set other) {
        vector<string> result;
        for (string elem : elements) {
            for (string otherElem : other.getElements()) {
                if (elem == otherElem) {
                    result.push_back(elem);
                    break;
                }
            }
        }
        return Set(result);
    }

    // **Complement** (U - A)
    Set complement(Set universalSet) {
        vector<string> result;
        for (string elem : universalSet.getElements()) {
            bool found = false;
            for (string myElem : elements) {
                if (elem == myElem) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.push_back(elem);
            }
        }
        return Set(result);
    }

    // **Cartesian Product** (A × B)
    Set cartesianProduct(Set other) {
        vector<string> result;
        for (string elemA : elements) {
            for (string elemB : other.getElements()) {
                result.push_back("(" + elemA + ", " + elemB + ")");
            }
        }
        return Set(result);
    }

    // **Equality Check** (A == B)
    bool isEqual(Set other) {
        if (elements.size() != other.getElements().size()) return false;
        for (string elem : elements) {
            bool found = false;
            for (string otherElem : other.getElements()) {
                if (elem == otherElem) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }
};

void getUserInput(vector<string>& universalSet, vector<string>& setA, vector<string>& setB) {
    int numElements;
    string element;

    cout << "Enter the number of elements in the universal set: ";
    cin >> numElements;
    cout << "Enter elements of the universal set:\n";
    for (int i = 0; i < numElements; i++) {
        cin >> element;
        universalSet.push_back(element);
    }

    cout << "Enter the number of elements in Set A: ";
    cin >> numElements;
    cout << "Enter elements of Set A:\n";
    for (int i = 0; i < numElements; i++) {
        cin >> element;
        setA.push_back(element);
    }

    cout << "Enter the number of elements in Set B: ";
    cin >> numElements;
    cout << "Enter elements of Set B:\n";
    for (int i = 0; i < numElements; i++) {
        cin >> element;
        setB.push_back(element);
    }
}

int main() {
    vector<string> universalSet, setA, setB;
    getUserInput(universalSet, setA, setB);

    Set U(universalSet), A(setA), B(setB);

    cout << "\nUniversal Set: ";
    U.display();
    cout << "Set A: ";
    A.display();
    cout << "Set B: ";
    B.display();

    // Implementation of operation selection and results display
    int choice;
    cout << "\nSelect a set operation:\n";
    cout << "1. Union (A U B)\n";
    cout << "2. Intersection (A n B)\n";
    cout << "3. Complement of A (U - A)\n";
    cout << "4. Complement of B (U - B)\n";
    cout << "5. Cartesian Product (A x B)\n";
    cout << "6. Check if A == B\n";
    cout << "Enter choice: ";
    cin >> choice;

    switch (choice) {
    case 1:
        cout << "A U B = ";
        A.unionWith(B).display();
        break;
    case 2:
        cout << "A n B = ";
        A.intersectionWith(B).display();
        break;
    case 3:
        cout << "U - A = ";
        A.complement(U).display();
        break;
    case 4:
        cout << "U - B = ";
        B.complement(U).display();
        break;
    case 5:
        cout << "A × B = ";
        A.cartesianProduct(B).display();
        break;
    case 6:
        if (A.isEqual(B)) {
            cout << "A and B are equal.\n";
        }
        else {
            cout << "A and B are not equal.\n";
        }
        break;
    default:
        cout << "Invalid choice.\n";
    }
    return 0;
}

