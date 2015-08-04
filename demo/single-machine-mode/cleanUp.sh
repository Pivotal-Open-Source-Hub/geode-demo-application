#!/bin/bash
# Randy May & Luke Shannon
cd $(dirname $0)
export clean_yes='Y'
echo "This will remove all GemFire working directories (disk persisted data will be lost). Are you sure?"
echo "Going to delete files in ../geode-server-package/"
echo "Also going to delete the Derby DB"
echo "Type '$clean_yes' to confirm 'C' to cancel"
read input
if [ "$input" == "$clean_yes" ]; then
	pkill -f java
	rm -r ../geode-server-package/serverA
	rm -r ../geode-server-package/serverB
	rm -r ../geode-server-package/serverC
	rm -r ../geode-server-package/serverD
	rm -r ../geode-server-package/locatorA
	rm -r ../geode-server-package/locatorB
	rm -fr fastfootshoes
fi
