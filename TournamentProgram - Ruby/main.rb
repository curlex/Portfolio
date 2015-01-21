require_relative 'player.rb'
require_relative 'tournament.rb'

tournament = Tournament.new

IO.foreach('Tournament.txt') do |line|
  data =  line.split # so data is an array of strings
  name = data[0]
  rating = data[1].to_i
  results = data[2..-1]#.gsub(/\s+/, "")
  player = Player.new(name, rating)
  player.add_result results
  #puts player.print_results
  #puts player.to_s
  tournament.add_player player
end
puts tournament.to_s
tournament.sort!
puts tournament.to_s
puts tournament.check_consistency

tournament.new_rating
puts tournament.to_s