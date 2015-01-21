require_relative 'random_player.rb'
require_relative 'oracle.rb'
require_relative 'smart_player.rb'
require_relative 'sequential_player.rb'

class GuessIt
  def initialize name1,name2,name3
    range = (0..100)
    random_range = (range.begin..range.end)
    @oracle = Oracle.new random_range
    @players =[]
    @players <<(RandomPlayer.new name1, @oracle ,random_range)
    @players<<(SmartPlayer.new name2, @oracle ,random_range)
    @players<<(SequentialPlayer.new name3,@oracle,random_range)

  end

  def set_up_game
    @players.each do |player|
      player.guess
      puts player.to_s
    end
    begin
        @players.each do |player|
          if(player.results!=0)
            player.guess
           puts  player.to_s
          end
      end
    end while !(@players[0].results==0 && @players[1].results==0 && @players[2].results==0)
  end

  def sort!
    @players.sort!{|player,player_two| player_two.number_of_guesses<=>player.number_of_guesses}
  end

  def to_s
    @players.inject("Players in ascending order:\n") { |str,player | str+=player.name+"\t"+player.number_of_guesses.to_s+"\n"}
  end

end

