#include <iostream>
#include <vector>
#include <limits>

using namespace std;

// Generatiing an arithmetic sequence
vector<double> generateArithmetic(double initialTerm, double difference, int numTerms) {
    vector<double> sequence;
    for (int i = 0; i < numTerms; ++i) {
        sequence.push_back(initialTerm + i * difference);
    }
    return sequence;
}

// Generating a geometric sequence
vector<double> generateGeometric(double initialTerm, double ratio, int numTerms) {
    vector<double> sequence;
    for (int i = 0; i < numTerms; ++i) {
        sequence.push_back(initialTerm * pow(ratio, i));
    }
    return sequence;
}

// Input validation
template <typename T>
void getValidInput(const string& prompt, T& value) {
    while (true) {
        cout << prompt;
        cin >> value;

        if (cin.fail()) {
            cout << "Invalid input. Please enter a valid number.\n";
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }
        else {
            break;
        }
    }
}

int main() {
    cout << "Sequence Generator: Arithmetic or Geometric\n";
    cout << "-------------------------------------------\n";

    string type;
    while (true) {
        cout << "Enter sequence type (A for Arithmetic / G for Geometric): ";
        cin >> type;
        if (type == "A" || type == "a" || type == "G" || type == "g") {
            break;
        }
        else {
            cout << "Invalid input. Please enter A or G.\n";
        }
    }

    double initialTerm;
    getValidInput("Enter the initial term: ", initialTerm);

    double step;
    if (type == "A" || type == "a") {
        getValidInput("Enter the common difference: ", step);
    }
    else {
        getValidInput("Enter the common ratio: ", step);
    }

    int numTerms;
    while (true) {
        getValidInput("Enter the number of terms to generate: ", numTerms);
        if (numTerms <= 0) {
            cout << "Number of terms must be greater than zero.\n";
        }
        else {
            break;
        }
    }

    vector<double> sequence;

    if (type == "A" || type == "a") {
        sequence = generateArithmetic(initialTerm, step, numTerms);
    }
    else {
        sequence = generateGeometric(initialTerm, step, numTerms);
    }

    cout << "\nGenerated Sequence:\n";
    for (double value : sequence) {
        cout << value << " ";
    }
    cout << "\n";

    return 0;
}