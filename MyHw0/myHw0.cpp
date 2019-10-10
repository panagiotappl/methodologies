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
  start = clock();
  for (string line; getline(cin, line);) {
      int idx = line.find("|");
      int a = stoi(line.substr(0, idx));
      int b = stoi(line.substr(idx + 1, line.length()));
      pair <int, int> p = make_pair(a, b);
      lines.push_back(p);
      if(m[a].empty()) {
        m[a][b] = 1;
      } else {
        if(!m[a][b]) {
          int len = m[a].size();
          m[a][b] = len;
        }
      }
  }
  for(int i = 0; i < lines.size(); i++) {
    int a = lines[i].first;
    int b = lines[i].second;
    string s = to_string(a) + "|" + to_string(b) + "[" + to_string(m[a][b]) + " of " + to_string(m[a].size()) + "]";
    cout << s << endl;
  }
  seconds = ( clock() - start ) / (double) CLOCKS_PER_SEC;
  cerr << seconds << "S" << endl;
  return 0;
}
