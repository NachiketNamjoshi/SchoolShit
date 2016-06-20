#include<iostream>
#include <sys/types.h>
#include <sys/stat.h>
#include <time.h>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

struct stat sb;

class stats {

public:
void prints();
void check(int, char *[]);
};
void stats::check(int argc, char *argv[]) {
if (argc != 2) {
 cout<<"Usage: "<<argv[0]<<" <pathname>\n";
 exit(EXIT_FAILURE);
    }

if (stat(argv[1], &sb) == -1) {
 perror("stat");
 exit(EXIT_FAILURE);
}
}
void stats::prints() {
cout<<"\n\nFile type:	";
    switch (sb.st_mode & S_IFMT) {
    case S_IFBLK:  cout<<"block device\n";     break;
    case S_IFCHR:  cout<<"character device\n"; break;
    case S_IFDIR:  cout<<"directory\n"; break;
    case S_IFIFO:  cout<<"FIFO/pipe\n"; break;
    case S_IFLNK:  cout<<"symlink\n";   break;
    case S_IFREG:  cout<<"regular file\n";     break;
    case S_IFSOCK: cout<<"socket\n";    break;
    default:cout<<"unknown?\n";  break;
    }
cout<<"I-node number:	"<<(long) sb.st_ino<<"\n";
cout<<"Mode:	"<<(unsigned long) sb.st_mode<<" (octal)\n";
cout<<"Link count:	"<<(long) sb.st_nlink<<"\n";
cout<<"Ownership:	UID="<<(long) sb.st_uid<<"\tGID= "<<(long) sb.st_gid<<"\n"; 
cout<<"Preferred I/O block size:	"<<(long) sb.st_blksize<<" bytes\n";
cout<<"File size:	"<<(long long) sb.st_size<<" bytes\n";
cout<<"Blocks allocated:	"<<(long long) sb.st_blocks<<"\n";


cout<<"Last status change:	"<<ctime(&sb.st_ctime);
cout<<"Last file access:	"<<ctime(&sb.st_atime);
cout<<"Last file modification:	"<<ctime(&sb.st_mtime);
}


int main(int argc, char *argv[]) {
stats S;
S.check(argc, argv);
S.prints();
cout<<"\n\n"; 
exit(EXIT_SUCCESS);
}

