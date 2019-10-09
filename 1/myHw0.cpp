#include <iostream>
#include <vector>
#include <map>
#include <ctime>
#include <unordered_map>

using namespace std;

int main() {
  vector<pair<int, int> > lines;
  unordered_map<int, unordered_map<int, int> > m;
  unordered_map<int, int> items;
  clock_t start;
  double seconds;
  int minutes, hours;

  start = std::clock();
  for (string line; getline(cin, line);) {
        int idx = line.find("|");
        int a = stoi(line.substr(0, idx));
        int b = stoi(line.substr(idx + 1, line.length()));
        pair <int, int> p = make_pair(a, b);
        lines.push_back(p);
        items = m[a];
        if(items.empty()) {
          items[b] = 1;
          m[a] = items;
        } else {
          if(!items[b]) {
            int len = items.size();
            items[b] = len;
            m[a] = items;
          }
        }
  }
  cerr << "Done processing" << endl;
  seconds = ( clock() - start ) / (double) CLOCKS_PER_SEC;
  cerr << seconds << endl;
  for(int i = 0; i < lines.size(); i++) {
    int a = lines[i].first;
    int b = lines[i].second;
    unordered_map<int, int> m1 = m[a];
    string s = to_string(a) + "|" + to_string(b) + "[" + to_string(m1[b]) + " of " + to_string(m1.size()) + "]";
    cout << s << endl;
  }
  seconds = ( clock() - start ) / (double) CLOCKS_PER_SEC;
  cerr << seconds << endl;
  return 0;
}
