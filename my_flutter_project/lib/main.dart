// main.dart
import 'package:flutter/material.dart';
import 'package:my_flutter_project/Classes.dart';
import 'package:my_flutter_project/DeezerApi.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  final DeezerApi deezerApi = DeezerApi();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Deezer Playlist Example'),
        ),
        body: FutureBuilder(
          // Pass the desired playlist URL
          future: deezerApi.fetchPlaylistFromUrl('https://api.deezer.com/playlist/908622995'),
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.waiting) {
              return Center(
                child: CircularProgressIndicator(),
              );
            } else if (snapshot.hasError) {
              return Center(
                child: Text('Error: ${snapshot.error}'),
              );
            } else {
              final Playlist playlist = snapshot.data ?? Playlist();

              return ListView.builder(
                itemCount: playlist.tracks.length,
                itemBuilder: (context, index) {
                  final Track track = playlist.tracks[index];

                  return Card(
                    child: ListTile(
                      title: Text(track.title),
                      subtitle: Text(track.artist.name),
                      // Add more details or widgets as needed
                    ),
                  );
                },
              );
            }
          },
        ),
      ),
    );
  }
}
