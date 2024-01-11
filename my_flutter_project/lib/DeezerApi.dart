// DeezerApi.dart
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:my_flutter_project/Classes.dart';

class DeezerApi {
  final String baseUrl = 'https://api.deezer.com';

  Future<Playlist> fetchPlaylistFromUrl(String playlistUrl) async {
    // Extract playlist ID from the URL
    final List<String> pathSegments = Uri.parse(playlistUrl).pathSegments;
    if (pathSegments.length >= 2) {
      final int playlistId = int.tryParse(pathSegments[pathSegments.length - 1]) ?? 0;

      // Fetch playlist using the ID
      final response = await http.get(Uri.parse('$baseUrl/playlist/$playlistId'));

      if (response.statusCode == 200) {
        final Map<String, dynamic> playlistJson = json.decode(response.body);
        return Playlist.fromJson(playlistJson);
      } else {
        throw Exception('Failed to load playlist');
      }
    } else {
      throw Exception('Invalid playlist URL');
    }
  }
}
